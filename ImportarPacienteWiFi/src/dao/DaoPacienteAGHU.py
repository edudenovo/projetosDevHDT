import psycopg2
from model.PacienteAGHU import PacienteAGHU

class DaoPacienteAGHU:
    
    def __init__(self, host, database, user, password, port):
        self.host = host
        self.database = database
        self.user = user
        self.password = password
        self.port = port
        
    def setHost(self, host):
        self.host = host
     
    def getHost(self):
        return self.host
    
    def setDatabase(self, database):
        self.database = database
     
    def getDatabase(self):
        return self.database
    
    def setUser(self, user):
        self.user = user
     
    def getUser(self):
        return self.user
    
    def setPassword(self, password):
        self.password = password
     
    def getPassword(self):
        return self.password
    
    def setPort(self, port):    
        self.port = port
     
    def getPort(self):
        return self.port
    
    def listarPaciente(self):
        con = psycopg2.connect(host=self.getHost(), database=self.getDatabase(), user=self.getUser(), password=self.getPassword(), port=self.getPort())
        cur = con.cursor()
        cur.execute("select lpad(cast(cpf as varchar),11,'0') cpf, rg from agh.aip_pacientes  where  cpf is not null and rg is not null and (criado_em > current_date or dt_recadastro > current_date)")
        recset = cur.fetchall()
        listaPaciente = []
        for rec in recset:
            paciente = object.__new__(PacienteAGHU)
            paciente._cpf = rec[0]
            paciente._rg = rec[1]                       
            listaPaciente.append(paciente)            
        con.close()
        
        return listaPaciente        