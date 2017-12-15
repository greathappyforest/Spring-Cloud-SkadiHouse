import requests
import json

#https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list

url = "https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list"

r = requests.get(url, stream=True)

count=0
data=""
for line in r.iter_lines():
    count=count+1
    python_obj = json.loads(line)
    obj_type = python_obj["type"]  
    if len(python_obj["export_address"])>0:      
        address= python_obj["export_address"][0]
    port = python_obj["port"]
    data += obj_type+"://"+str(address)+ ":"+str(port) +"\n"
print "Toral proxies updated: "+str(count)
with open('proxy_list.txt', 'w') as f:
    f.write(data)