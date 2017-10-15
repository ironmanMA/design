import fileinput


def toTime(time):
    min = time / 60
    sec = time % 60
    minApp = "minute"
    secApp = "second"
    delim = " "
    if (sec == 0 | sec > 1):
        secApp = "seconds"
    if (min > 1):
        minApp = "minutes"
    if (min != 0):
        return str(min) + delim + minApp + delim + str(sec) + delim + secApp
    return str(sec) + delim + secApp


def nextInp():
    curr += 1
    return lines[curr - 1]


if __name__ == '__main__':
    global lines
    global curr
    lines = []
    curr = 0
    for line in fileinput.input():
        lines.append(line)
    cases = nextInp()
    for x in xrange(int(cases)):
        row_col = nextInp()
        rows = int(row_col.split(" ")[0])
        cols = int(row_col.split(" ")[1])
        virusLoc = nextInp()
        virusLoc = virusLoc[1:- 1]
        strArr = virusLoc.split(",")
        virusLocRow = int(strArr[0])
        virusLocCol = int(strArr[1])
        len_V_00 = max(abs(virusLocRow - 1), abs(virusLocCol - 1))
        len_V_0C = max(abs(virusLocRow - 1), abs(virusLocCol - cols))
        len_V_R0 = max(abs(virusLocRow - rows), abs(virusLocCol - 1))
        len_V_RC = max(abs(virusLocRow - rows), abs(virusLocCol - cols))
        timeToSpread = max(len_V_00, len_V_0C, len_V_R0, len_V_RC)
        # print(rows + ", " + cols + ", Row:" + virusLocRow + ", Col:" + virusLocCol + ", " +"TimeSpread:" + (timeToSpread))
        print(toTime(timeToSpread))
        #  cases = raw_input()
        # for x in xrange(int(cases)):
        #     row_col=raw_input()
        #     rows = int(row_col.split(" ")[0])
        #     cols = int(row_col.split(" ")[1])
        #     virusLoc = raw_input()
        #     virusLoc = virusLoc[1:- 1]
        #     strArr = virusLoc.split(",")
        #     virusLocRow = int(strArr[0])
        #     virusLocCol = int(strArr[1])
        #     len_V_00 = max(abs(virusLocRow - 1), abs(virusLocCol - 1))
        #     len_V_0C = max(abs(virusLocRow - 1), abs(virusLocCol - cols))
        #     len_V_R0 = max(abs(virusLocRow - rows), abs(virusLocCol - 1))
        #     len_V_RC = max(abs(virusLocRow - rows), abs(virusLocCol - cols))
        #     timeToSpread = max(len_V_00, len_V_0C,len_V_R0, len_V_RC)
        #     # print(rows + ", " + cols + ", Row:" + virusLocRow + ", Col:" + virusLocCol + ", " +"TimeSpread:" + (timeToSpread))
        #     print(toTime(timeToSpread))
