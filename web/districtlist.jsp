<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<option value="">Select District</option>
<c:forEach items="${DistrictList}" var="district" >
    <option value=${district.getDistrictCode()}<c:if test="${district.getDistrictCode().equalsIgnoreCase(emp.getDistrictCode())}" > selected </c:if>>
        ${district.getDistrict()} 
    </option>
</c:forEach>