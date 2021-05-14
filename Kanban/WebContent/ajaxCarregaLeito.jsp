<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<c:forEach var="kanban" items="${resposta}">
	{"leito":"${kanban.getLeito()}","nome":"${kanban.getNomecompleto()}","prontuario":"${kanban.getProntuario()}"}	
</c:forEach>