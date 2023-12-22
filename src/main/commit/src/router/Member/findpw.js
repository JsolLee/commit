import { React, useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import './member.css';
import { useNavigate } from 'react-router-dom';

function FindPw() {
  const [validated, setValidated] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      navigate("/Login");
    }

    setValidated(true);
  };
  return (
    <div className="FindPw member">
      <Form className='findpw' noValidate validated={validated} onSubmit={handleSubmit}>
        <h1 className='text-center'>비밀번호 재발급</h1>
        <br/>
        <p>
            회원 가입시 입력한 이메일을 입력하세요.<br/>
            임시 비밀번호를 이메일로 보내드립니다.<br/>
        </p>
        <br />
        <Form.Group className="mb-3" controlId="formGroupEmail">
          <Form.Label>이메일 주소</Form.Label>
          <Form.Control type="email" placeholder="이메일을 입력해주세요." required/>
          <Form.Control.Feedback type="invalid">
              이메일을입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <br/>
        <Button className="mb-3" variant="primary" type="submit">
          비밀번호 재설정하기
        </Button>
      </Form>
    </div>
  );
}

export default FindPw;
