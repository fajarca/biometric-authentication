# Biometric Authentication

On this simple project I explored how biometric authentication is implemented on Android. 

Biometric authentication is one of the available method to protect sensitive information within our app. The biometric authentication helps us to utilize fingerprint recognition and face recognition sensor on the device.
![Output](https://drive.google.com/drive/u/0/folders/1tZNcjteMZ5NF3UUYYz6jMAEQwPAsD3I0)
# Integration

To integrate Biometric Authentication on our project the steps are simple. Firstly, we need to add the following line under the dependencies tag in `build.gradle` at the app level. 

> dependencies {  
implementation 'androidx.biometric:biometric:1.0.1'  
}
