cd /home/zbo/Opt/apache-tomcat-8.0.33/bin
sh shutdown.sh
cp -rf /home/zbo/Code/spring-petclinic/src/main/webapp/* /home/zbo/Opt/apache-tomcat-8.0.33/webapps/ROOT/
sh startup.sh
