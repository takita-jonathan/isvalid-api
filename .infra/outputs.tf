output "ec2_public_ip" {
  value = aws_instance.app.public_ip
  description = "IP público da instância EC2"
}

output "ec2_private_ip" {
  value = aws_instance.app.private_ip
  description = "O IP privado da instância EC2"
}

output "ec2_instance_id" {
  value = aws_instance.app.id
}