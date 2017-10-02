import time

TO_FILE_NAME = "/var/log/rboomerang/testing-lozio-log.log"
# FROM_FILE_NAME = "/Users/127.0.0.1.ma/boom-pi-work/product-matcher/product_matcher/logs/validator_small.log"
# FROM_FILE_NAME = "/Users/127.0.0.1.ma/boom-pi-work/automatcher/ProductMatcher/logs/matching_small.log"
FROM_FILE_NAME = "/Users/127.0.0.1.ma/boom-pi-work/scrap/testing-logio.log"

with open(FROM_FILE_NAME) as from_file:
    with open(TO_FILE_NAME, 'a') as to_file:
        for line in from_file.readlines():
            time.sleep(0.01)
            print line
            to_file.write(line)
