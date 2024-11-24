<html>
<%@include file="../bootstrap.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/script/add-carello.js"></script>
<body>

<%@ page import="java.util.*,model.*"  %>
<%@ page import="model.Farmaco" %>

<jsp:include page="header-client.jsp" />


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
            <div class="container-fluid ">
                <form method="POST" action="ordini-cliente" id="ordine-form" class="container-fluid">
                   <h5 class="pr-5 mr-5 py-3" ><strong> <u>ORDINE</u></strong></h5>
                   <% if(session.getAttribute("quantitaErrorMessage")!=null) { %>
                       <p class="text-danger"> <%= session.getAttribute("quantitaErrorMessage") %> </p>
                   <% session.setAttribute("quantitaErrorMessage", null); } %>
                   <button class="btn btn-success" id="valida_ordine" type="submit" onclick="validOrdine()">VALIDA ORDINE</button>
                   <div class="row my-3">
                        <div class="col-5">
                            <b> Nome Farmaco</b>
                        </div>
                        <div class="col-2">
                            <b> Prezzo (Euro)</b>
                        </div>
                        <div class="col-3">
                            <b> Quantita Voluta</b>
                        </div>
                        <div class="col-2">
                            <b> Azione</b>
                        </div>
                   </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
