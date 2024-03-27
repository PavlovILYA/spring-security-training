#!/bin/bash

#keytool -importkeystore -deststorepass changeit -destkeypass changeit -destkeystore keystore.jks -srckeystore store/mycert-private.p12 -srcstoretype PKCS12 -srcstorepass my -alias vladssl -destalias localhost

keytool -import -alias localhost -keystore store/truststore.jks -file store/mycert.crt

#keytool -keystore store/keystore.jks -genkey -alias localhost -keyalg rsa

#keytool -importkeystore -srckeystore cert.pfx -srcstoretype pkcs12 -destkeystore keystore.jks -deststoretype pkcs12
