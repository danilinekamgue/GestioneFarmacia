<html>

<%@include file="../bootstrap.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="/test/script/add-carello.js"></script>

<%@ page import="java.util.*,model.*"  %>
<body>
<jsp:include page="header-client.jsp" />


    <div class="container">
        <div class="row flex d-flex justify-content-center">
            <div class="col-6">
                <h5 class="text-center mt-3 pb-3"> LISTA DEGLI ORDINI </h5>
              <%
                  ArrayList<OrdineModel> ordini=(ArrayList<OrdineModel>) session.getAttribute("ordini");
                  if(ordini != null){
                    for (OrdineModel ordine: ordini) {
              %>
                  <div class="mt-2 p-5 border">
                      <p> <strong> Ordine : O<%=ordine.getNumero()%> <br /> Data <%=ordine.getTime()%> </strong> </p>
                      <h6 class="my-3"> Lista dei prodotti </h6>
                        <table class="table table-bordered">
                        <%
                           for (Farmaco farmaco: ordine.getFarmaci()) {
                        %>

                        <tr>
                          <td><%=farmaco.getNome()%></td>
                          <td> x <%=farmaco.getQuantita()%></td>
                        </tr>

                        <% } %>
                        </table>
                        <h7> <strong> Prezzo totale : <%=ordine.getPrezzototale()%>  Euro </strong></h7>
                      </div>
              <% }} %>

              <%
                 if(ordini == null){
              %>
                    Non ci sono ordini
              <% } %>

            </div>
        </div>
    </div>

</body>
</html>
