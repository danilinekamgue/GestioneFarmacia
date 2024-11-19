<html>
<%@include file="../bootstrap.jsp" %>
<script type="text/javascript" src="/test/script/add-carello.js"></script>
<body>

<%@ page import="java.util.*,model.*"  %>


    <form action = "logout" method = "POST">
        <button onclick="addCarello(1)"> LOGOUT </button>
    </form>
<p>Vous êtes <%= session.getAttribute("email")  %>  !</p>

<div class="container-fluid">
    <div class="row">
        <div class="col-7">
            <table class="table">
                <thead>
                  <tr>
                    <th>Nome</th>
                    <th>Descrizione</th>
                    <th>Prezzo</th>
                    <th>Azione</th>
                  </tr>
                </thead>
              <%
                  ArrayList<FarmacoModel> formaci=(ArrayList<FarmacoModel>) session.getAttribute("farmaci");
                  if(formaci != null){
                    for (FarmacoModel post: formaci) {
              %>
                <tr>
                  <td><%=post.getNome()%></td>
                  <td><%=post.getId()%></td>
                  <td><%=post.getId()%></td>
                  <td> <button  onclick="addCarello( <%=post.getId()%>, <%=post.getId()%>, <%=post.getId()%>, <%=post.getId()%>)"> + add </button></td>
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

</body>
</html>
