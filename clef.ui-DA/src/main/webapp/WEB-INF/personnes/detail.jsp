<%@page contentType="text/html" pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%--

--%><%@page import="clef.routing.ActionPage"%><%--
--%><%@page import="clef.routing.EtatPage"%><%--
--%><%@page import="clef.routing.PersonneUtils"%><%--

--%><!DOCTYPE html>
<html>
    <%@include file="../jspf/entete.jspf" %>
    <body>
        <%@include file="../jspf/navbar.jspf" %>
        <section>
            <header>
                <h1>Personnes</h1>
            </header>
            <c:set var="current_dir" value="personnes" />
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


                        <label for="nom">nom</label>
                        <input id="nom"
                               name="nom"
                               value="${detail.nom}"
                               placeholder="nom"
                               <c:if test="${EtatPage.VISUALISATION == etatPage or 
                                             EtatPage.SUPPRESSION == etatPage}">
                                     readonly=""
                               </c:if>/>

                        <label for="prenom">prénom</label>
                        <input id="prenom"
                               name="prenom"
                               value="${detail.prenom}"
                               placeholder="prénom"
                               <c:if test="${EtatPage.VISUALISATION == etatPage or 
                                             EtatPage.SUPPRESSION == etatPage}">
                                     readonly=""
                               </c:if>/>


                        <label for="email">e-mail</label>
                        <input id="email"
                               name="email"
                               value="${detail.email}"
                               placeholder="email"
                               <c:if test="${EtatPage.VISUALISATION == etatPage or 
                                             EtatPage.SUPPRESSION == etatPage}">
                                     readonly=""
                               </c:if>/>

                        <div style="grid-column: 1 / 3 ;">
                            <h2>Liste des clefs</h2>
                            <div class="item-list" style="display: grid; grid-template-columns: 1fr 5fr;">
                                <div class="grid-item header">action</div>
                                <div class="grid-item header">Clefs actives</div>
                                <div class="grid-item"><a href="#">voir</a></div>
                                <div class="grid-item">0000-0000-0000-0002</div>
                                <div class="grid-item"><a href="#">voir</a></div>
                                <div class="grid-item">0000-0000-0000-0012</div>
                                <div class="grid-item"><a href="#">voir</a></div>
                                <div class="grid-item">0000-0000-0000-0034</div>
                            </div>
                            </br>
                            <h2>Liste des lieux</h2>
                            <div class="item-list" style="display: grid; grid-template-columns: 1fr 3fr;">
                                <div class="grid-item header">action</div>
                                <div class="grid-item header">Droit d'accès</div>
                                <div class="grid-item"><a href="../lieux/DEMO0000-0000-0000-0003-000000000020.html">voir</a></div>
                                <div class="grid-item">CIFOM - Etage 3 - 315</div>
                                <div class="grid-item"><a href="../lieux/DEMO0000-0000-0000-0003-000000000030.html">voir</a></div>
                                <div class="grid-item">CPLN - Etage 1 - B102</div>
                                <div class="grid-item"><a href="../lieux/DEMO0000-0000-0000-0003-000000000030.html">voir</a></div>
                                <div class="grid-item">CPLN - Etage 2</div>
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </section>
    </body>
</html>