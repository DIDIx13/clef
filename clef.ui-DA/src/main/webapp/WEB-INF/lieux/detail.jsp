<%@page contentType="text/html" pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%--

--%><%@page import="clef.routing.ActionPage"%><%--
--%><%@page import="clef.routing.EtatPage"%><%--
--%><%@page import="clef.routing.LieuUtils"%><%--

--%><!DOCTYPE html>

<html>
    <%@include file="../jspf/entete.jspf" %>
    <body>
        <%@include file="../jspf/navbar.jspf" %>
        <section>
            <header>
                <h1>Lieux</h1>
            </header>
            <c:set var="current_dir" value="lieux" />
            <%@include file="../jspf/search.jspf" %>
            <section>
                <h2>Détail</h2>
                <form method="POST">
                    <div class="grid-form">
                        <%@include file="../jspf/crud_buttons.jspf" %>
                        <small>id: <c:out value="${detail.identifiant.UUID}"/></small>
                        <small>version: <c:out value="${detail.identifiant.version}"/></small>

                        <input hidden="" name="${LieuUtils.JSP_ATTRIBUT_UUID}" 
                               value="${detail.identifiant.UUID}"/>
                        <input hidden="" name="${LieuUtils.JSP_ATTRIBUT_VERSION}" 
                               value="${detail.identifiant.version}"/>
                        <label for="nom">nom</label>
                        <input id="nom"
                               name="nom"
                               value="${detail.nom}"
                               placeholder="nom"
                               <c:if test="${EtatPage.VISUALISATION == etatPage or 
                                             EtatPage.SUPPRESSION == etatPage}">
                                     readonly=""
                               </c:if>/>
                        <label for="lieu_parent_select">lieu parent</label>
                        <select name="${LieuUtils.JSP_LIEU_PARENT_UUID}" id="lieu_parent_select" 
                                <c:if test="${EtatPage.VISUALISATION == etatPage or 
                                              EtatPage.SUPPRESSION == etatPage}"> disabled </c:if>>
                                <c:forEach var="lieuParent" items="${liste}">
                                    <c:if test="${lieuParent.identifiant.UUID != detail.identifiant.UUID}">
                                        <option value="${lieuParent.identifiant.UUID}">${lieuParent.nom}</option>
                                    </c:if>
                                </c:forEach> 
                                <option selected value="${detail.lieuParent.identifiant.UUID}">${detail.lieuParent.nom}</option>
                        </select>
                        <c:if test="${EtatPage.MODIFICATION == etatPage}">
                        <button type="reset" value="Supprimer">Supprimer</button>
                        </c:if>
                    </div>
                </form>
            </section>
        </section>
    </body>

</html>