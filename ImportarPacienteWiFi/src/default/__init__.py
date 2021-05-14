import threading
import sched, time

import psycopg2
import pymysql

from dao.DaoPacienteAGHU import DaoPacienteAGHU
from dao.DaoPacienteWiFi import DaoPacienteWiFi

pacienteWiFi = DaoPacienteWiFi('10.98.0.120', 'radius', 'root', 'ebserh@hdt', 3306)    
pacienteAGHU = DaoPacienteAGHU('10.98.0.19', 'dbaghu', 'ugen_integra', 'aghuintegracao', '6544')  
      
listaPaciente = []    
listaPaciente = pacienteAGHU.listarPaciente()    
for lista in listaPaciente:
    pacienteWiFi.insertPacienteWiFi(lista._cpf, lista._rg)    



