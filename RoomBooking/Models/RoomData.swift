//
//  RoomData.swift
//  RoomBooking
//
//  Created by Weiping Tok on 30/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import Foundation

struct RoomData: Codable {
    let name: String
    let capacity: String
    let level: String
    let availability: [String: String]
}


