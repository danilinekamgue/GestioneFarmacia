


<%@include file="../bootstrap.jsp" %>
<jsp:include page="admin-header.jsp" />
<%@ page import="java.util.*,model.*"  %>

<div class="container-fluid">
    <div class="row d-flex justify-content-center">
        <div class="col-10">
            <h2  class="text-center m-3">LISTA DEI FARMACI</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Descrizione</th>
                        <th>Prezzo</th>
                        <th>Quantità</th>
                        <th>Actions</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                    List<Farmaco> farmaci = (List<Farmaco>) request.getAttribute("farmaci");
                    for (Farmaco farmaco : farmaci) {
                    %>
                        <tr>
                            <td><%= farmaco.getId() %></td>
                            <td><%= farmaco.getNome() %></td>
                            <td><%= farmaco.getDescrizione() %></td>
                            <td><%= farmaco.getPrezzo() %></td>
                            <td><%= farmaco.getQuantita() %></td>
                             <td>
                                <!-- Pulsante di aggiornamento -->
                                <a href="updateFarmaco?id=<%= farmaco.getId() %>" class="btn btn-primary btn-sm">U</a>

                                <!-- Pulsante di eliminazione -->
                                <form action="deletefarmaco" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= farmaco.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">X</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="text-center mt-3">
                <a href="addfarmaco.jsp" class="btn btn-success btn-lg mb-5">ADD FARMACO</a>
            </div>

        </div>
    </div>
</div>
