//
//  RoomManager.swift
//  RoomBooking
//
//  Created by Weiping Tok on 30/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

protocol RoomManagerDelegate {
    func didDownloadRooms(_ roomManager: RoomManager, rooms: [RoomModel])
    func didFailWithError(error: Error)
}

import Foundation

struct RoomManager {
    let roomsURL = "https://gist.githubusercontent.com/yuhong90/7ff8d4ebad6f759fcc10cc6abdda85cf/raw/463627e7d2c7ac31070ef409d29ed3439f7406f6/room-availability.json"
    
    var delegate: RoomManagerDelegate?
    
    func fetchRooms() {
        //print(roomsURL)
        performRequest(with: roomsURL)
    }
    
    func performRequest(with urlString: String) {
        //1. Create a URL
        if let url = URL(string: urlString) {
            //2. Create a URLSession
            let session = URLSession(configuration: .default)
            //3. Give the session a task
            let task = session.dataTask(with: url) { (data, urlResponse, error) in
                if error != nil {
                    //print(error!)
                    self.delegate?.didFailWithError(error: error!)
                    return
                }
                
                if let safeData = data {
                    //let dataString = String(data: safeData, encoding: .utf8)
                    //print(dataString!)
                    if let rooms = self.parseJSON(safeData) as? [RoomModel]{
                        self.delegate?.didDownloadRooms(self, rooms: rooms)
                    }
                }
            }
            //4. Start the task
            task.resume()
        }
        
    }
    
    func parseJSON(_ roomData: Data) -> [RoomModel?] {
        let decoder = JSONDecoder()
        //print("parseJSON")
        
        do {
            let decodedDataArray = try decoder.decode([RoomData].self, from: roomData)
            var rooms = [RoomModel]()
            decodedDataArray.forEach {
                //print($0)
                let name = ($0).name
                let capacity = Int(($0).capacity) ?? 0
                let level = Int(($0).level) ?? 0
                let availability = ($0).availability
                var decodedAvailability = [String: Int]()
                for item in availability {
                    decodedAvailability.updateValue(Int(item.value) ?? 0, forKey: item.key)
                }
                //print(decodedAvailability)
                let room = RoomModel(roomName: name, roomCapacity: capacity, roomLevel: level, roomAvailability: decodedAvailability)
                rooms.append(room)
            }
            
            return rooms
            
        } catch {
            //print(error)
            self.delegate?.didFailWithError(error: error)
            return [nil]
        }
    }
}
