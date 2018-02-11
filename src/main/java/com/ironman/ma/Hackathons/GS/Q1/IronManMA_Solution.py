# Required Python Packages
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix
from sklearn.externals import joblib
from sklearn.metrics import f1_score

import pdb

# File Paths
INPUT_PATH = "/Users/mohammadarafath/Personal/scrap/data/adjusted-train.csv"
OUTPUT_PATH = "/Users/mohammadarafath/Personal/scrap/modified-train.data"
TEST_INPUT_PATH = "/Users/mohammadarafath/Personal/scrap/data/test.csv"
TEST_MODIFIED_INPUT_PATH = "/Users/mohammadarafath/Personal/scrap/data/mod_test.csv"
MODEL_PATH = "/Users/mohammadarafath/Personal/scrap/ironmanma_model.pkl"
TEST_PREDICTION_PATH = "/Users/mohammadarafath/Personal/scrap/prediction.csv"
TRAIN_PREDICTION_PATH = "/Users/mohammadarafath/Personal/scrap/train_prediction.csv"

# Headers
HEADERS = ["buying_price", "maintainence_cost", "number_of_doors", "number_of_seats", "luggage_boot_size",
           "safety_rating", "popularity"]


def read_data(path):
    data = pd.read_csv(path)
    return data


def get_headers(dataset):
    return dataset.columns.values


def add_headers(dataset, headers):
    dataset.columns = headers
    return dataset


def data_file_to_csv():
    # Headers
    headers = ["buying_price", "maintainence_cost", "number_of_doors", "number_of_seats", "luggage_boot_size",
               "safety_rating", "popularity"]
    # Load the dataset into Pandas data frame
    dataset = read_data(INPUT_PATH)
    # Add the headers to the loaded dataset
    dataset = add_headers(dataset, headers)
    # Save the loaded dataset into csv format
    dataset.to_csv(OUTPUT_PATH, index=False)
    print ("File saved ...!")


def data_file_to_test_csv():
    # Headers
    headers = ["buying_price", "maintainence_cost", "number_of_doors", "number_of_seats", "luggage_boot_size",
               "safety_rating"]
    # Load the dataset into Pandas data frame
    dataset = read_data(TEST_INPUT_PATH)
    # Add the headers to the loaded dataset
    dataset = add_headers(dataset, headers)
    # Save the loaded dataset into csv format
    dataset.to_csv(TEST_MODIFIED_INPUT_PATH, index=False)
    print ("File saved ...!")


def split_dataset(dataset, train_percentage, feature_headers, target_header):
    # Split dataset into train and test dataset
    train_x, test_x, train_y, test_y = train_test_split(dataset[feature_headers], dataset[target_header],
                                                        train_size=train_percentage)
    return train_x, test_x, train_y, test_y


def random_forest_classifier(features, target):
    clf = RandomForestClassifier(criterion="entropy")
    clf.fit(features, target)
    return clf


def dataset_statistics(dataset):
    print (dataset.describe())


def main():
    data_file_to_csv()
    data_file_to_test_csv()
    # Load the csv file into pandas dataframe
    dataset = pd.read_csv(OUTPUT_PATH)
    dataset_Predict = pd.read_csv(TEST_MODIFIED_INPUT_PATH)
    # Get basic statistics of the loaded dataset
    dataset_statistics(dataset)

    # Filter missing values
    # dataset = handel_missing_values(dataset, HEADERS[6], '?')

    train_x, test_x, train_y, test_y = split_dataset(dataset, 0.75, HEADERS[0:-1], HEADERS[-1])

    # Train and Test dataset size details
    print ("Train_x Shape :: ", train_x.shape)
    print ("Train_y Shape :: ", train_y.shape)
    print ("Test_x Shape :: ", test_x.shape)
    print ("Test_y Shape :: ", test_y.shape)

    # Create random forest classifier instance
    trained_model = random_forest_classifier(train_x, train_y)
    joblib.dump(trained_model, MODEL_PATH)
    trained_model = joblib.load(MODEL_PATH)
    print(trained_model.feature_importances_)
    print ("Trained model :: ", trained_model)
    predictions = trained_model.predict(test_x)
    actPredictions = trained_model.predict(dataset_Predict)

    for i in range(0, 1185):
        if list(test_y)[i] != predictions[i]:
            print ("Actual :: {} and Predicted :: {}".format(list(test_y)[i], predictions[i]))

    print ("Train Accuracy :: ", accuracy_score(train_y, trained_model.predict(train_x)),
           f1_score(train_y, trained_model.predict(train_x), average='macro'))
    print ("Test Accuracy  :: ", accuracy_score(test_y, predictions), f1_score(test_y, predictions, average='macro'))

    print (" Confusion matrix \n", confusion_matrix(test_y, predictions))
    pd.DataFrame(actPredictions).to_csv(TEST_PREDICTION_PATH, index=False)
    pd.DataFrame(predictions).to_csv(TRAIN_PREDICTION_PATH, index=False)


if __name__ == "__main__":
    main()
