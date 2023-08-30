
# Dockerfile: Docker üzeridnen image oluşturmak içindir
# image: Projenin bütün config dosyaların ve projenin ayağa kalkacağı ayar dosyalarıdır
# container: image'in ayağa kaldırmak için kullanıyoruz
# docker-daemon: bu docker image vs işlemlerinin bulunduğu yönetildiği yerdir.
# Volume: copntainerlar loose-coupling olarak çalışır yani birbirinden bağımsız çalışır.
# network:  çalışma alanıdır. Projelerimizin birbirine bağlayacak yerdir.

# JDK KURULUMU
FROM openjdk:17

# Bilgilendirme
LABEL maintainer="hamitmizrak@gmail.com"

# Persist Data(Kalıcı Veriyi sağlamak)
VOLUME /tmp

# Harici Port
EXPOSE 4444

# Jar yerini göster
ARG JAR_FILE=target/*.jar

# Değişken olarak Ata
ADD ${JAR_FILE} spring_react_blog

# Point
ENTRYPOINT [ "java","-jar","/spring_react_blog" ]