<%@ include file = "/jspf/header.jspf" %>

<c:set var='view' value='/artistartworks' scope='session' />

<h2><fmt:message key='artworksby' /> ${selectedArtist.firstname} ${selectedArtist.surname} </h2>

    <c:forEach var="artwork" items="${artistartworks}" varStatus="iter">
        <img src="${initParam.artworkImagePath}${artwork.coverimage}.png"
             alt="No cover image found" width="250" height="300">
        <br><br>
        <h1><fmt:message key="${artwork.title}"/></h1>
        <h2><fmt:message key='workmedium' /> ${artwork.medium}</h2>
    </c:forEach>

<%@ include file = "/jspf/footer.jspf" %>