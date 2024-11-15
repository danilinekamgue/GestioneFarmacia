<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista Farmaci</title>
</head>
<body>
    <h2>Lista dei Farmaci</h2>
    <a href="controller/Farmaco?action=new">Aggiungi Nuovo Farmaco</a>
    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Descrizione</th>
                <th>Quantità</th>
                <th>Prezzo</th>
                <th>Data Scadenza</th>
                <th>Fornitore</th>
                <th>Azione</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="farmaco" items="${listaFarmaci}">
                <tr>
                    <td>${farmaco.nome}</td>
                    <td>${farmaco.descrizione}</td>
                    <td>${farmaco.quantita}</td>
                    <td>${farmaco.prezzo}</td>
                    <td>${farmaco.dataScadenza}</td>
                    <td>${farmaco.fornitore}</td>
                    <td>
                        <a href="farmaci?action=edit&id=${farmaco.id}">Modifica</a>
                        <a href="farmaci?action=delete&id=${farmaco.id}">Elimina</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
