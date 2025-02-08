provider "aws" {
  region     = "sa-east-1"
}

terraform {
  backend "s3" {
    bucket         = "challenger-terraform-state-bucket"
    key            = "terraform.tfstate"
    encrypt        = true
    region         = "sa-east-1"
  }
}