#!/usr/bin/python

from gcmclient import gcm

apikey = 'AIzaSyAI1Bq3Wkf83ClPpyWwH_B3tWxcblk14Vc'
regid = [
    'APA91bFfTWIQWBSY7vS1Kxr_jQ8IyeYSGBKp7kw4hNgzYv_tnsNjiMm2kuVfrYFkGS4_o7ur_Opl2SMGYSPOk5bWdtCE7OYr9dlSaxzDuvBGCYTPP9QHFOuk_gTOUM63V2bQoC8So08XXFav99piiJ6P8HZcpjvNAA'
]


conn = gcm.GCM(apikey)
# In the future, this will have to be changed
data = {
    "title": "HELLO, WORLD!",
    "message": "Hello to Java from Python!"
}
msg = gcm.JSONMessage(regid, data)
try:
    ret = conn.send(msg)

    if ret.success:
        print "Success:", ret.success

    if ret.failed:
        print "Failure:", ret.failed
except gcm.GCMAuthenticationError:
    print "YOU DUN GOOF SON"
