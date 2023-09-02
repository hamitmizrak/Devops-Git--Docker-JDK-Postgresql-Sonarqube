public class ButunKurulum {

sudo apt-get update && sudo apt-get upgrade -y  && sudo apt-get dist-upgrade
sudo apt install net-tools
sudo apt-get install vim -y
sudo apt install curl -y
sudo apt-get install unrar -y
sudo apt-get install openssh-server -y
sudo apt-get install rar
-------------------------------------
sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install git -y
git version

git config --global user.name "hamitmizrak"
git config --global user.email "hamitmizrak@gmail.com"
git config --global -l
-----------------------------------
sudo apt-get update
sudo apt install openjdk-11-jdk -y
sudo add-apt-repository ppa:openjdk-r/ppa
vim ~/.bashrc
#Java Home
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/bin/"
source ~/.bashrc
echo $JAVA_HOME
java -version
-----------------------------------
sudo apt-get update
cd /usr/local
sudo wget https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz
sudo tar xzf apache-maven-3.9.4-bin.tar.gz
sudo ln -s apache-maven-3.9.4 maven

sudo vi /etc/profile.d/maven.sh
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 export M2_HOME=/usr/local/maven
export MAVEN_HOME=/usr/local/maven export PATH=${M2_HOME}/bin:${PATH}

source ~/.bashrc
source /etc/profile.d/maven.sh

echo $MAVEN_HOME
mvn -version
-----------------------------------
sudo apt-get purge docker-ce docker-ce-cli containerd.io -y
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd

sudo apt-get remove docker docker-engine docker.io containerd runc

sudo apt-get update
sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common -y
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io -y

sudo systemctl status docker
sudo systemctl enable --now docker
sudo systemctl start docker
sudo systemctl status docker
-----------------------------------
sudo curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

sudo docker volume create portainer_data
sudo docker run -d -p 2222:9000 -p 8000:8000 --name portainer --restart always -v /var/run/docker.sock:/var/run/docker.sock -v /srv/portainer:/data portainer/portainer
sudo docker start portainer
sudo docker stop portainer

admin
Hm123456789.
-----------------------------------
sudo apt update
sudo apt upgrade
sudo snap install code --classic
-----------------------------------
sudo apt-get update
sudo wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get install jenkins -y

sudo service jenkins stop

sudo vim /etc/default/jenkins
JENKINS_ARGS  ==> HTTP_PORT=3333

sudo service jenkins restart
sudo systemctl status jenkins
sudo service jenkins status

admin
root

github
oracle
maven Integration
sonar Scanner

-----------------------------------
# Postgresql
sudo apt update  && sudo apt upgrade -y
sudo wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | sudo apt-key add -
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'
sudo apt install postgresql postgresql-contrib -y
sudo -u postgres psql -c "SELECT version();"
\q

sudo systemctl enable postgresql.service
sudo systemctl start  postgresql.service
sudo systemctl status postgresql.service

sudo passwd postgres
#Şifrem: sonar

su - postgres
sonar
createuser sonar
psql
ALTER USER sonar WITH ENCRYPTED PASSWORD 'sonar';
CREATE DATABASE sonarqube OWNER sonar;
GRANT ALL PRIVILEGES ON DATABASE sonarqube to sonar;
\q
exit

sudo systemctl restart  postgresql
sudo systemctl status -l   postgresql
sudo netstat -tulpena | grep postgres

-----------------------------------
sudo vim /etc/sysctl.conf
# SonarQube
vm.max_map_count=262144
fs.file-max=65536
ulimit -n 65536
ulimit -u 4096

sudo vim /etc/security/limits.conf
# SonarQube
sonarqube   -   nofile   65536
sonarqube   -   nproc    4096

JDK kurulu olmalıdır.
Postgresql kurulumu (Mysql artık kullanmıyoruz)

sudo mkdir /sonarqube/
cd /sonarqube/
sudo curl -O https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-8.3.0.34182.zip
sudo apt-get install zip
sudo unzip sonarqube-8.3.0.34182.zip -d /opt/
sudo mv /opt/sonarqube-8.3.0.34182/ /opt/sonarqube
sudo groupadd sonar
sudo useradd -c "SonarQube - User" -d /opt/sonarqube/ -g sonar sonar
sudo chown sonar:sonar /opt/sonarqube/ -R

sudo vim /opt/sonarqube/conf/sonar.properties
# SonarQube
sonar.jdbc.username=sonar
sonar.jdbc.password=sonar
sonar.jdbc.url=jdbc:postgresql://localhost/sonarqube
sonar.web.host=127.0.0.1
sonar.web.port=9000
sonar.web.javaAdditionalOpts=-server
sonar.search.javaOpts=-Xmx512m -Xms512m -XX:+HeapDumpOnOutOfMemoryError
sonar.log.level=INFO
sonar.path.logs=logs

sudo vim /etc/systemd/system/sonarqube.service
#SonarQube
[Unit]
Description=SonarQube service
After=syslog.target network.target
[Service]
Type=forking
ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop
User=sonar
Group=sonar
Restart=always
LimitNOFILE=65536
LimitNPROC=4096
[Install]
WantedBy=multi-user.target


sudo systemctl daemon-reload
sudo systemctl enable sonarqube.service
sudo systemctl start sonarqube.service
sudo systemctl status -l sonarqube.service
sudo systemctl stop sonarqube.service
sudo systemctl restart sonarqube.service
sudo netstat -tulpena  | grep 9000

sudo ufw allow 9000/tcp
reboot

sudo systemctl enable sonarqube
sudo systemctl start sonarqube
sudo systemctl stop sonarqube
sudo systemctl restart sonarqube
sudo systemctl status sonarqube

username: admin
password: admin
-----------------------------------
sudo apt-get update
sudo apt-get clean
sudo apt-get autoremove -y
}
