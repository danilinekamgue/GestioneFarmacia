


<%@include file="../bootstrap.jsp" %>
<jsp:include page="admin-header.jsp" />
<%@ page import="java.util.*,model.*"  %>

<div class="container-fluid">
    <div class="row d-flex justify-content-center">
        <div class="col-10">
            <h2  class="text-center m-3">LISTA DEGLI UTENTI</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>

                            <th>Email</th>
                            <th>Nome</th>

                            <th>Role</th>
                            <th>Password</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        List<Client> clients = (List<Client>) request.getAttribute("clients");
                        for (Client client : clients) { %>
                            <tr>

                                 <td><%= client.getEmail() %></td>
                                <td><%= client.getNome() %></td>

                                <td><%= client.getRole() %></td>
                                <td><%= client.getPassword() %></td>
                                <td>
                                    <!-- Pulsante di aggiornamento -->
                                    <a href="updateclienti?email=<%= client.getEmail() %>" class="btn btn-primary btn-sm">U</a>

                                    <!-- Pulsante di eliminazione -->
                                    <form action="deleteclienti" method="post" style="display:inline;">
                                        <input type="hidden" name="email" value="<%= client.getEmail() %>">
                                        <button type="submit" class="btn btn-danger btn-sm">X</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                 <div class="text-center my-5">
                    <a href="addclient.jsp" class="btn btn-success btn-lg">Add User</a>
                </div>
        </div>
    </div>
</div>
