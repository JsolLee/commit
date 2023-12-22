import React from 'react';
import { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import './member.css';
import {useNavigate} from 'react-router-dom';

function Join() {
  const [validated, setValidated] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    } else{
      navigate("/Login");
    }
    
    setValidated(true);
    
  };
  return (
    <div className="Join member">
      <Form className='join' noValidate validated={validated} onSubmit={handleSubmit}>
        <h1 className='text-center'>회원가입</h1>
        <br />
        <Form.Group className="mb-3" controlId="formGroupId">
          <Form.Label>아이디</Form.Label>
          <Form.Control type="text" placeholder="아이디를 입력해주세요." required />
          <Form.Control.Feedback type="invalid">
            아이디를 입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupPassword">
          <Form.Label>비밀번호</Form.Label>
          <Form.Control type="password" placeholder="비밀번호를 입력해주세요." required />
          <Form.Control.Feedback type="invalid">
            비밀번호를 입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupEmail">
          <Form.Label>이메일</Form.Label>
          <Form.Control type="email" placeholder="이메일을 입력해주세요." required />
          <Form.Control.Feedback type="invalid">
            이메일을 올바르게 입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupNickname">
          <Form.Label>닉네임</Form.Label>
          <Form.Control type="text" placeholder="닉네임을 입력해주세요." required />
          <Form.Control.Feedback type="invalid">
            닉네임을 입력해주세요.
          </Form.Control.Feedback>
        </Form.Group>
        <br />
        <Button className="mb-3" variant="primary" type="submit">
          회원가입
        </Button>
      </Form>
      <br />
    </div>
  );
}

export default Join;
