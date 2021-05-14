#!/bin/sh   
set -e   
case "$1" in   
  start)   
          echo "Carregando Programa"   
          su -c /root/teste/java -jar teste.jar &   
          ;;   
  stop)   
          echo "Encerrando Programa"   
          killall java   
          ;;   
     *)   
          echo "Usage: /etc/init.d/teste {start|stop}"   
          exit 1   
esac   
exit 0