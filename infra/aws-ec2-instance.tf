resource "aws_instance" "app" {
  ami           = "ami-0c820c196a818d66a"
  instance_type = "t2.micro"
  key_name      = "my-ec2-key"
  security_groups = [aws_security_group.is-valid-sg.name]

  lifecycle {
    ignore_changes = [user_data]
  }
}

resource "aws_security_group" "is-valid-sg" {
  name        = "is-valid-sg"
  description = "Permitir acesso HTTP e SSH"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}