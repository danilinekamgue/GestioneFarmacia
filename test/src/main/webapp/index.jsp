<html>
<%@include file="../bootstrap.jsp" %>
<script type="text/javascript" src="/test/script/add-carello.js"></script>
<body>
<!-- -->
< %= java expressoon %>
<%@ page import="java.util.*,model.*"  %>
< % java ficntion variable code %>
< %! declare variable %>
<h2 class="bg-warning">FARMACIA DANIELLA</h2>

        <button onclick="addCarello(1)"> LOGIN </button>
    <form action = "login" method = "GET">
        <button onclick="addCarello(1)"> LOGIN </button>
    </form>
     <h5>LISTA DEI PRODOTTI</h5>

     <%String name = (String)request.getAttribute("tttt"); %>
     <%= name %>


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
                  ArrayList<FarmacoModel> formaci=(ArrayList<FarmacoModel>) request.getAttribute("farmaci");
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
