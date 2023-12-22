import React, {useState} from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {Link, useNavigate} from 'react-router-dom';

function Login() {
  const [validated, setValidated] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      navigate("/");
    }

    setValidated(true);  
  };
  
  return (
    <div className="Login member">
      <Form className='login' noValidate validated={validated} onSubmit={handleSubmit}>
        <h1 className='text-center'>로그인</h1>
        <br />
        <Form.Group className="mb-3" controlId="formGroupEmail">
          <Form.Label>아이디</Form.Label>
          <Form.Control type="text" placeholder="아이디를 입력해주세요." required/>
          <Form.Control.Feedback type="invalid">
              아이디를 입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupPassword">
          <Form.Label>비밀번호</Form.Label>
          <Form.Control type="password" placeholder="비밀번호를 입력해주세요." required/>
          <Form.Control.Feedback type="invalid">
              비밀번호를 입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <br/>
        <Button className="mb-3" variant="primary" type="submit">
          로그인
        </Button>
        <Form.Group className='text-center link'>
        <Link to='/join'>회원가입 | </Link>
        <Link to='/findid'>아이디 찾기 | </Link>
        <Link to='/findpw'>비밀번호 찾기 </Link>
        </Form.Group>
      </Form>
    </div>
  );
}

export default Login;
