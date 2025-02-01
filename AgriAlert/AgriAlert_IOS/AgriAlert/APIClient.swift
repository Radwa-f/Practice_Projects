//
//  APIClient.swift
//  AgriAlert
//
//  Created by Fattouhi Radwa on 23/12/2024.
//

import Alamofire

class APIClient {
    static let shared = APIClient() // Singleton
    private init() {}

    private let baseURL = "http://localhost:8087/" // Replace with your actual base URL

    func login(email: String, password: String, completion: @escaping (Result<String, Error>) -> Void) {
        let url = "\(baseURL)api/v1/login"
        let parameters = LoginRequest(email: email, password: password)

        AF.request(url, method: .post, parameters: parameters, encoder: JSONParameterEncoder.default)
            .validate() // Validate status code
            .responseString { response in
                switch response.result {
                case .success(let token):
                    completion(.success(token)) // Return the token
                case .failure(let error):
                    completion(.failure(error)) // Return the error
                }
            }
    }
    func registerUser(request: RegistrationRequest, completion: @escaping (Result<Void, Error>) -> Void) {
            let url = "\(baseURL)api/v1/registration"
            
            AF.request(url, method: .post, parameters: request, encoder: JSONParameterEncoder.default)
                .validate() // Automatically validates the response status code
                .response { response in
                    switch response.result {
                    case .success:
                        completion(.success(()))
                    case .failure(let error):
                        completion(.failure(error))
                    }
                }
        }
}
