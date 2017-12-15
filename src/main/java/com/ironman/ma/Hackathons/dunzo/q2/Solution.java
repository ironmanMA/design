package com.ironman.ma.Hackathons.dunzo.q2;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 127.0.0.1.ma on 15/12/17.
 */
/*
{
  "page": "2",
  "per_page": 10,
  "total": 13,
  "total_pages": 2,
  "data": [
    {
      "Poster": "N/A",
      "Title": "They Call Me Spiderman",
      "Type": "movie",
      "Year": "2016",
      "imdbID": "tt5861236"
    },
    {
      "Poster": "N/A",
      "Title": "The Death of Spiderman",
      "Type": "movie",
      "Year": "2015",
      "imdbID": "tt5921428"
    },
    {
      "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BZDlmMGQwYmItNTNmOS00OTNkLTkxNTYtNDM3ZWVlMWUyZDIzXkEyXkFqcGdeQXVyMTA5Mzk5Mw@@._V1_SX300.jpg",
      "Title": "Spiderman in Cannes",
      "Type": "movie",
      "Year": "2016",
      "imdbID": "tt5978586"
    }
  ]
}
 */

public class Solution {

    static final String API_ENDPOINT = "https://jsonmock.hackerrank.com/api/movies/search/?Title=%s&page=%d";
    static Gson gson = new Gson();

    static String getResponse(String GET_URL) throws IOException {
        String responseAsString = "{}";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseAsString = response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        return responseAsString;
    }

    static MovieObjList getMovieObjList(String substr, int pageNum) throws IOException {
        String API_ENDPOINT_ACT = String.format(API_ENDPOINT, substr, pageNum);
        String firstResponse = getResponse(API_ENDPOINT_ACT);
        return gson.fromJson(firstResponse, MovieObjList.class);
    }

    static String[] getMovieTitles(final String substr) {
        String[] movieList = null;

        int pageToCrawl = 1;
        int pagesCrawledTillNow = 0;
        boolean isCrawl = false;
        MovieObjList movieObjList = null;
        int remainingFutures = 0;
        try {
            movieObjList = getMovieObjList(substr, pageToCrawl);
            movieList = new String[movieObjList.totalMovies];
            for (MovieObj movieObj : movieObjList.data) {
                movieList[pagesCrawledTillNow] = movieObj.title;
                pagesCrawledTillNow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletionService<MovieObjList> completionService =
                new ExecutorCompletionService<MovieObjList>(executor);

        for (int i = 2; i <= movieObjList.totalPages; i++) {
            remainingFutures++;
            final int finalI = i;
            completionService.submit(new Callable<MovieObjList>() {
                public MovieObjList call() {
                    MovieObjList movieObjList = null;
                    try {
                        movieObjList = getMovieObjList(substr, finalI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return movieObjList;
                }
            });
        }

        while (remainingFutures > 0) {
            try {
                Future<MovieObjList> completedFuture = completionService.take();
                MovieObjList movieObjListFuture = completedFuture.get();
                for (MovieObj movieObj : movieObjListFuture.data) {
                    movieList[pagesCrawledTillNow] = movieObj.title;
                    pagesCrawledTillNow++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            remainingFutures--;
        }
        executor.shutdown();
//        while (isCrawl) {
//            try {
//                String API_ENDPOINT_ACT = String.format(API_ENDPOINT, substr, pageToCrawl);
//                String firstResponse = getResponse(API_ENDPOINT_ACT);
//                MovieObjList movieObjList = gson.fromJson(firstResponse, MovieObjList.class);
//                if (movieObjList.currentPage == 1) {
//                    movieList = new String[movieObjList.totalMovies];
//                }
//                if (movieObjList.currentPage == movieObjList.totalPages) {
//                    isCrawl = false;
//                }
//                for (MovieObj movieObj : movieObjList.data) {
//                    movieList[pagesCrawledTillNow] = movieObj.title;
//                    pagesCrawledTillNow++;
//                }
//                pageToCrawl++;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        Arrays.sort(movieList);
        return movieList;
    }

    public static void main(String[] args) {
        String[] movies = getMovieTitles("spiderman");
        for (String s : movies) {
            System.out.println(s);
        }
    }

    public class MovieObj {
        @SerializedName("Title")
        public String title;
    }

    public class MovieObjList {
        @SerializedName("data")
        public List<MovieObj> data;
        @SerializedName("total")
        int totalMovies;
        @SerializedName("total_pages")
        int totalPages;
        @SerializedName("page")
        int currentPage;
    }
}
