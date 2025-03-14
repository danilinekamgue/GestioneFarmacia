
<%@include file="../bootstrap.jsp" %>
<jsp:include page="admin-header.jsp" />
<%@ page import="java.util.*,model.*"  %>

<div class="container-fluid">
    <div class="row d-flex justify-content-center">
        <div class="col-6">
            <h2  class="text-center m-3">LISTA DEGLI ORDINI</h2>
                <%
                    ArrayList<OrdineModel> ordini=(ArrayList<OrdineModel>) request.getAttribute("ordini");
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

