<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<option value="">Select State</option>
<c:forEach items="${ProvinceList}" var="state" >
    <option value=${state.getStateCode()}<c:if test="${state.getStateCode().equalsIgnoreCase(emp.getStateCode())}" > selected </c:if>> 
        ${state.getStateName()} 
    </option>
</c:forEach>
