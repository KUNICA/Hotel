<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>




<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>


<div>
    <table id="wideTable">
        <tr>
        </tr>
        <tr>
            <td align="right">
                <span id="alert"></span>
            </td>
            <td></td>
        </tr>
        <tr>
            <td align="right"><sf:errors path="text" /></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2">
                <table

                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table >
                    <tr>
                        <td align="left">

                        </td>
                        <td align="right">

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        document.getElementById('message_text').focus();
    </script>
    <script type='text/javascript'>
        function textLength(value) {
            var maxLength = 140;
            if (value.length > maxLength)
                return false;
            return true;
        }
        var oldValue = '';
        var alert = document.getElementById('alert');
        document.getElementById('message_text').onkeyup = function() {
            if (!textLength(this.value)) {
                this.value = oldValue;
                alert.innerHTML = 'Text is too long!'
            } else {
                oldValue = this.value;
                alert.innerHTML = ''
            }
        }
    </script>
</div>
