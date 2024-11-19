<html>
<%@ page import="java.util.*,model.*"  %>
<body>

<h1> ORDINE </h1>

              <%
                  ArrayList<OrdineModel> ordini=(ArrayList<OrdineModel>) session.getAttribute("ordini");
                  if(ordini != null){
                    for (OrdineModel ordine: ordini) {
              %>
                  <div><%=ordine.getNumero()%></div>

              <% }} %>

              <%
                    if(ordini == null){
                  %>
                    Non ci sono ordini
                  <% } %>


</body>
</html>
