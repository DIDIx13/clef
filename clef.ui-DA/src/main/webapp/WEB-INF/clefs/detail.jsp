<%@page contentType="text/html" pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%--

--%><%@page import="clef.routing.ActionPage"%><%--
--%><%@page import="clef.routing.EtatPage"%><%--
--%><%@page import="clef.routing.ClefUtils"%><%--

--%><!DOCTYPE html>
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
                <h2>Détail</h2>
                <form method="POST">
                    <div class="grid-form">
                        <%@include file="../jspf/crud_buttons.jspf" %>
                        <small>id: <c:out value="${detail.identifiant.UUID}"/></small>
                        <small>version: <c:out value="${detail.identifiant.version}"/></small>

                        <input hidden="" name="${ClefUtils.JSP_ATTRIBUT_UUID}" 
                               value="${detail.identifiant.UUID}"/>
                        <input hidden="" name="${ClefUtils.JSP_ATTRIBUT_VERSION}" 
                               value="${detail.identifiant.version}"/>

                        <label for="numeroserie">numeroserie</label>
                        <input id="numeroserie"
                               name="numeroserie"
                               value="${detail.numeroSerie}"
                               placeholder="numeroserie"
                               <c:if test="${EtatPage.VISUALISATION == etatPage or 
                                             EtatPage.SUPPRESSION == etatPage}">
                                     readonly=""
                               </c:if>/>

                        <label for="status">status</label>
                        <c:choose>
                            <c:when test="${EtatPage.VISUALISATION == etatPage or EtatPage.SUPPRESSION == etatPage}">
                                <input id="status" name="status" value="${detail.clefStatus}" placeholder="status" readonly=""/>
                            </c:when>
                            <c:otherwise>
                                <select name="${ClefUtils.JSP_ATTRIBUT_STATUS}" id="status">
                                    <c:forEach var="status" items="${liste}">
                                        <c:if test="${status.identifiant.UUID != detail.identifiant.UUID}">
                                            <option value="${status.identifiant.UUID}" <c:if test="${status.identifiant.UUID == detail.clefStatus}">selected</c:if>>${status.nom}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${EtatPage.MODIFICATION == etatPage}">
                            <button type="reset" value="Supprimer">Supprimer</button>
                        </c:if>
                    </div>
                </form>
            </section>
        </section>
    </body>
</html>