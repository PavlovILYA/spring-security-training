
###Installing and using HTTPS

We need to install SSL certificate.
In our case, we will use a self-signed certificate.

1) Create a keystore with a private key of certificate:

```
keytool -importkeystore -srckeystore cert.pfx -destkeystore keystore.jks
```

Enter destination keystore password: **changeit**
Re-enter new password: **changeit**
Enter source keystore password: **password you used to download pfx**

2) Create a truststore 
```
keytool -import -keystore truststore.jks -file cert.crt
```
Enter keystore password: **changeit**

3) To see the installed certificates, you can use
```
keytool -v -list -keystore keystore.jks
```

