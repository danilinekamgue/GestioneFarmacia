<html>
<%@include file="../bootstrap.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="/test/script/add-carello.js"></script>
<body>

<%@ page import="java.util.*,model.*"  %>
<%@ page import="model.Farmaco" %>

  <div class="container mt-4">

   <form action="logout" method="POST">
            <button type="submit" class="btn btn-danger">
                <i class="fas fa-sign-out-alt"></i> Logout
            </button>
   </form>
<p>Vous Ãªtes <%= session.getAttribute("email")  %>  !</p>

<div class="container-fluid">
    <div class="row">
        <div class="col-7">
            <table   class="table table-bordered">
                <thead >
                  <tr>
                    <th>Nome</th>
                    <th>Descrizione</th>
                    <th>Prezzo</th>
                    <th>Azione</th>
                  </tr>
                </thead>
              <%
                  ArrayList<Farmaco> formaci=(ArrayList<Farmaco>) session.getAttribute("farmaci");
                  if(formaci != null){
                	  System.out.println("àààààà");
                    for (Farmaco post: formaci) {
              %>
                <tr>
                  <td><%=post.getNome()%></td>
                  <td><%=post.getId()%></td>
                  <td><%=post.getId()%></td>
                  <td> <button onclick="addCarello(<%=post.getId()%>)" class="btn btn-success">
                                            <i class="fas fa-plus"></i> Add
                                        </button></td>
                </tr>
              <% }} %>
                  <%
                    if(formaci == null){
                  %>
                     <tr><td align="center" colspan="4"> Non ci sono medici disponibili ! </td></tr>
                  <% } %>
            </table>
        </div>

        <div class="col-5">
            <div class="container-fluid bg-success">
                <h5>ORDINE</h5>

            <form method="POST" action="ordine" id="ordine-form" class="container-fluid">
               <div class="row">
                    <div class="col-2">
                        <b> HEADER</b>
                    </div>
                    <div class="col-2">
                        <b> HEADER</b>
                    </div>
                    <div class="col-2">
                        <b> HEADER</b>
                    </div>
                    <div class="col-2">
                        <b> HEADER</b>
                    </div>
                    <div class="col-2">
                        <b> HEADER</b>
                    </div>
               </div>

                    <button id="valida_ordine" type="submit" onclick="prepareOrdine()">VALIDA ORDINE</button>



             </form>
            </div>
        </div>
    </div>
</div>
</div>
</div>

</body>
</html>
