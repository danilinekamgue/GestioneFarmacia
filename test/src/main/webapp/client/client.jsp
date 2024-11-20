<html>
<%@include file="../bootstrap.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="/test/script/add-carello.js"></script>
<body>

<%@ page import="java.util.*,model.*"  %>
<%@ page import="model.Farmaco" %>

<nav class="navbar navbar-success bg-success">
  <h5 class="navbar-brand">Benvenuto <%= session.getAttribute("email")  %> ! </h5>
  <form action="do.logout" method="POST" class="form-inline my-2 my-lg-0">
    <button class="btn btn-danger my-2 my-sm-0" type="submit" >
     <i class="fas fa-sign-out-alt"></i> Logout
    </button>
  </form>
</nav>



<div class="container-fluid mt-5">
    <div class="row">
        <div class="col-md-7">
            <h4 class="text-center"> <u>Lista dei farmaci disponibili </u></h4>

            <table class="table table-bordered mt-4">
                <thead >
                  <tr>
                    <th>Nome</th>
                    <th>Descrizione</th>
                    <th>Quantita</th>
                    <th>Prezzo(Euro)</th>
                    <th>Azione</th>
                  </tr>
                </thead>
                  <%
                      ArrayList<Farmaco> formaci=(ArrayList<Farmaco>) session.getAttribute("farmaci");
                      if(formaci != null){
                        for (Farmaco farmaco: formaci) {
                  %>
                    <tr>
                      <td><%=farmaco.getNome()%></td>
                      <td><%=farmaco.getDescrizione()%></td>
                      <td><%=farmaco.getQuantita()%></td>
                      <td><%=farmaco.getPrezzo()%></td>
                      <td> <button onclick="addCarello(<%=farmaco.getId()%>, '<%=farmaco.getNome()%>', <%=farmaco.getPrezzo()%> )" class="btn btn-success">
                                <i class="fas fa-plus"></i> Add
                            </button>
                      </td>
                    </tr>
                  <% }} %>

                  <%
                    if(formaci == null){
                  %>
                     <tr><td align="center" colspan="4"> Non ci sono medici disponibili ! </td></tr>
                  <% } %>
            </table>
        </div>

        <div class="col-md-5">
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
                   </div>
                   <button id="valida_ordine" type="submit" onclick="prepareOrdine()">VALIDA ORDINE</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
