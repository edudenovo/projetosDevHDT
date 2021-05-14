import pymysql

class DaoPacienteWiFi:
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
    
    def buscarPaciente(self, cpf):        
        conn = pymysql.connect(host=self.getHost(), port=self.getPort(), user=self.getUser(), passwd=self.getPassword(), db=self.getDatabase())
        cur = conn.cursor()
        select_query = "SELECT username FROM radcheck where username = %s"
        record_to_select = (cpf)
        cur.execute(select_query, record_to_select)        
        recset = cur.fetchall()
        retorno = 0
        for rec in recset:
            retorno = rec[0]        
        cur.close()
        conn.close()
        return retorno
        
    def insertPacienteWiFi(self, cpf, rg):
        paciente = self.buscarPaciente(cpf)
        conn = pymysql.connect(host=self.getHost(), port=self.getPort(), user=self.getUser(), passwd=self.getPassword(), db=self.getDatabase())
        cur = conn.cursor()
        if paciente == 0:
            insert_query = "insert into radcheck (username, attribute, op, value) values(%s, 'Cleartext-Password', ':=', %s)" 
            record_to_insert = (cpf, rg)
            cur.execute(insert_query, record_to_insert)
        else:
            update_query = "update radcheck set value = %s where username = %s" 
            record_to_update = (rg, cpf)
            cur.execute(update_query, record_to_update)
        conn.commit()               
        cur.close()
        conn.close()