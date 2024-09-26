<%@page contentType="text/html" pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%--

--%><%@page import="java.util.List"%><%--
--%><%@page import="clef.domain.Clef"%><%--

--%><c:set var="baseUrl" value="${pageContext.request.contextPath}/clefs"/><%--

--%>
<!DOCTYPE html>
<html>
    <%@include file="../jspf/entete.jspf" %>
    <body>
        <%@include file="../jspf/navbar.jspf" %>
        <section>
            <header>
                <h1>Clefs</h1>
            </header>
            <c:set var="current_dir" value="clefs" />
            <%@include file="../jspf/search.jspf" %>
            <section>
                <h2>Liste</h2>
                <form method="POST">
                    <button name="action" value="CREER">
                        Ajouter une nouvelle clef...
                    </button>
                </form>

                <div class="item-list">
                    <c:forEach var="detail" items="${liste}">
                        <article class="item">
                            <header>
                                <small>${detail.identifiant.UUID}</small>
                                <h3><a href="${pageContext.request.contextPath}/clefs/${detail.identifiant.UUID}.html" >
                                        ${detail.numeroSerie} : ${detail.clefStatus}</a></h3>
                            </header>
                        </article>
                    </c:forEach>
                </div>
            </section>
        </section>
    </body>
</html>

