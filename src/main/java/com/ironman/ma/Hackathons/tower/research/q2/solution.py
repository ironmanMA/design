# find unique IPs from log file
# IP=10.23.4.5:0

#
# import fileinput
# import re
#
# ipDic={}
#
# for line in fileinput.input():
#     # print line.split("ip=")
#     m = re.search('ip=(.*):', line)
#     if m:
#         found = m.group(1)
#         ipDic[found]=1
#     pass
# print len(ipDic)
