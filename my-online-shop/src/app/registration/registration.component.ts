import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  user = {
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    hashcode: ''
  };

  constructor(private http: HttpClient) { }

  onSubmit() {
    this.http.post<CustomerResponse>('http://localhost:8080/customers/create', this.user)
      .subscribe({
        next: response => console.log('User registered:', response),
        error: error => console.error('Registration error:', error),
        complete: () => console.log('Request completed')
      });
  }
}

interface CustomerResponse {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  createdAt: string;
  email: string;
}

