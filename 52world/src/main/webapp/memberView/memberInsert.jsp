<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원정보입력</title>
  </head>
  <body>
    <h3>회원가입</h3>
    <form id="joinForm" method="post" action="../memberInsert.do">
      <span style="color: red; font-weight: bold"></span>

      <div id="contain">
        <div class="idGroup">
          <h3 class="idTitle">
            <label for="id">아이디</label>
            <input
              type="text"
              name="id"
              id="id"
              size="20"
              placeholder="4~15자리의 영문과 숫자로 입력"
            />
            <button type="button" onclick="idCheck();">중복체크</button>
          </h3>
        </div>

        <div class="passwordGroup">
          <h3 class="passwordTitle">
            <label for="password">비밀번호</label>
            <input
              type="password"
              name="password"
              id="password"
              size="20"
              placeholder="8자리 이상 입력"
              required
            />
          </h3>
        </div>

        <div class="nameGroup">
          <h3 class="namedTitle">
            <label for="name">이름</label>
            <input type="text" name="name" id="name" size="20" required />
          </h3>
        </div>

        <div class="birthGroup">
          <h3 class="birthTitle">
            <label for="birth">생년월일</label>
            <select id="컬럼명" name="컬럼명" class="form-control">
              <option value="">년</option>
              <c:forEach var="i" begin="1900" end="2022">
                <option value="${i}">${i}</option>
              </c:forEach>
            </select>

            <select id="컬럼명" name="컬럼명" class="form-control">
              <option value="">월</option>
              <c:forEach var="i" begin="1" end="12">
                <c:choose>
                  <c:when test="${i lt 10 }">
                    <option value="0${i}">0${i}</option>
                  </c:when>
                  <c:otherwise>
                    <option value="${i}">${i}</option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>

            <select id="컬럼명" name="컬럼명" class="form-control">
              <option value="">일</option>
              <c:forEach var="i" begin="1" end="31">
                <c:choose>
                  <c:when test="${i lt 10 }">
                    <option value="0${i}">0${i}</option>
                  </c:when>
                  <c:otherwise>
                    <option value="${i}">${i}</option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>
          </h3>
        </div>

        <div class="genderGroup">
          <h3 class="genderTitle">
            <label for="gender">성별</label>
            <input
              type="radio"
              name="userGender"
              autocomplete="off"
              value="남자"
            />남

            <input
              type="radio"
              name="userGender"
              autocomplete="off"
              value="여자"
              checked
            />여
          </h3>
        </div>

        <div class="emailGroup">
          <h3 class="emailTitle">
            <label for="eamil">이메일</label>
            <input
              type="text"
              id="email_id"
              name="email_Id"
              value=""
              title="아이디"
              placeholder="이메일 입력"
              maxlength="18"
            />
            <span>@</span>
            <input
              type="text"
              id="email_domain"
              name="email_domain"
              value=""
              title="이메일 주소"
              placeholder="이메일을 선택하세요"
              maxlength="18"
            />
            <select
              class="select"
              id="emailList"
              title="이메일 주소 선택"
              onchange="return checkEmail()"
            >
              <option value="" disabled selected>Email 선택</option>
              <option value="naver.com">naver.com</option>
              <option value="gmail.com">gmail.com</option>
              <option value="hanmail.net">hanmail.net</option>
              <option value="nate.com">nate.com</option>
              <option value="" id="textEmail">직접 입력하기</option>
            </select>
          </h3>
        </div>

        <div class="phoneGroup">
          <h3 class="phoneTitle">
            <label for="phone">전화번호</label>
            <input type="tel" name="phone" id="phone" size="15" placeholder="숫자만 입력해주세요." onkeyup="mobile_keyup(this)" required />
            <button type="button" onclick="phoneCheck();">중복체크</button>
          </h3>
        </div>
      </div>

      <input type="submit" value="가입하기" />
    </form>

    <script>
      function idCheck(e) {
        /* window.open("./idCheckForm.jsp","idCheckWin","width=400 height=350") */
        //중복확인을 누르면 발생하는 이벤트가 this
        consol.log(this);
        let id = this;

        let checkIdAjx = new XMLHttpRequest();

        //입력방식은 post 방식
        checkIdAjx.open("post", "checkIdAjax.do");
        checkIdAjx.setRequestHeader(
          "Content-type",
          "application/x-www-form-urlencoded"
        );
        checkIdAjx.send("id=" + delId);
      }

      checkIdAjx.onload = function () {
        let result = JSON.parse(checkIdAjx.responseText);
        if (result.retCode == "Success") {
          arlet("중복된 아이디입니다");
        } else {
          arlet("사용할 수 있는 아이디입니다");
        }
      };

      function phoneCheck() {
        window.open(
          "./phoneCheckForm.jsp",
          "phoneCheckWin",
          "width=400 height=350"
        );
      }

      //이메일
      function checkEmail() {
        let domain = document.getElementById("emailList").value;

        if (domain != "") {
          document.getElementById("email_domain").value = domain;
        } else {
          document.getElementById("email_domain").focus();
          document.getElementById("email_domain").value = "";
        }
      }
      
      //  Mobile  - 하이픈 자동 생성 
      function mobile_keyup(obj){
          let mobile_len=obj.value.length;
          console.log(mobile_len)
          if(event.keyCode==8){
              obj.value=obj.value.slice(0,mobile_len); 
              return 0; 
          }else if (mobile_len==3 || mobile_len==8){
              obj.value += '-';
          }
      }
    </script>
  </body>
</html>
