<!doctype html>

<%@ page import="java.util.List, sbp.compare.Person, sbp.compare.PersonExceptions"%>

<html>
<head>
        <title>Conference IT</title>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<h2>Registered Persons:<h2>

<body>

<%
        List<Person> person = (List<Person>) request.getAttribute("allPersonList");
        if (person != null) {
            out.write("<ol>");
            for (int i = 0; i < person.size(); i++) {
              out.write("<li>" + person.get(i).getName() + " " + person.get(i).getCity() + "</li>");
            }
            out.write("</ol>");
        } else {
            out.write("New Person is registered!");
        }
%>

<hr>
<a href="/app">Home</a>
<a href="/app/api/create">Create new person</a>
</body>
</html>
