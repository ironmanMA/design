import pandas as pd
from sklearn.externals import joblib
import sys

# MODEL_PATH = "./ironmanma_model.pkl"
MODEL_PATH = "/Users/mohammadarafath/Personal/scrap/ironmanma_model.pkl"
TEST_PREDICTION_PATH = "./prediction.csv"


def data_file_to_test_csv(file_name):
    # Headers
    headers = ["buying_price", "maintainence_cost", "number_of_doors", "number_of_seats", "luggage_boot_size",
               "safety_rating"]
    # Load the dataset into Pandas data frame
    dataset = pd.read_csv(file_name)
    # Add the headers to the loaded dataset
    dataset.columns = headers
    return dataset


if __name__ == "__main__":
    TEST_FILE_NAME = sys.argv[1]
    test_dataset = data_file_to_test_csv(TEST_FILE_NAME)
    trained_model = joblib.load(MODEL_PATH)
    predictions = trained_model.predict(test_dataset)
    pd.DataFrame(predictions).to_csv(TEST_PREDICTION_PATH, index=False)
    print ("Finished")
