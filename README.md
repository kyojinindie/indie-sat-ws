# Descarga Masiva SAT

![Image of KyojinIndie](https://github.com/kyojinindie/indie-sat-ws/blob/master/kyojinIndie.png)

Este es un ejemplo de como consumir los 4 web services para la descarga masiva del SAT.

Todos los creditos son para estos 3 enlaces en los cuales fue basado este proyecto:

-https://developers.sw.com.mx/knowledge-base/descarga-masiva-sat-authenticacion/
-https://github.com/geezylucas/DescargaMasivaSAT
-https://developers.sw.com.mx/knowledge-base/como-crear-un-pfx/

### **USO**

1.- git clone https://github.com/kyojinindie/indie-sat-ws.git

2.-Convertir el archivo .key en .pem
	openssl pkcs8 -inform der -in Mi_archivo.key -passin pass:12345678a -out key.pem
	
3.-Convertir el archivo .cer en .pem
	openssl x509 -inform der -in Mi_archivo.cer -out cer.pem
	
4.- Convertir los archivos key.pem y cer.pem en .pfx
	openssl pkcs12 -export -in cer.pem -inkey key.pem -out Mi_archivo.pfx
	
5.- Llenar el archivo Util con las rutas de los archivos Mi_archivo.cer, Mi_archivo.pfx y el RFC correspondiente.

6.- Cambiar las fechas del archivo Service método consumeRequestDownload
	```java
		rqDownload.setInitialDate("2020-07-01");
		rqDownload.setFinalDate("2020-12-11");	
	```

7.- Run as Java application


This is a open source project.

Open source is a development methodology; free software is a social movement.

Happy Hacking :)
