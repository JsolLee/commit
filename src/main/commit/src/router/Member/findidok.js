import React  from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import './member.css';

function FindId() {
  return (
    <div className="FindId member">
      <Form className='findidok'>
        <h1 className='text-center'>아이디 찾기</h1>
        <br/>
        <p>
            가입하신 아이디는 아래와 같습니다.<br/>
        </p>
        <br />
        <Form.Group className="mb-3" controlId="formGroupEmail">
          <Form.Label>아이디</Form.Label> : yesri123
        </Form.Group>
        <br/>
        <Button href='/Login' className="mb-3" variant="primary">
          로그인 
        </Button>
        <br/>
        <Button href='/findpw' className="mb-3" variant="primary">
          비밀번호 찾기
        </Button>
      </Form>
    </div>
  );
}

export default FindId;
