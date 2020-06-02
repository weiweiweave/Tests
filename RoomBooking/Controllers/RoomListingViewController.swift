//
//  RoomListingViewController.swift
//  RoomBooking
//
//  Created by Weiping Tok on 30/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import UIKit

class RoomListingViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var datePickerOutlet: UITextField!
    
    @IBOutlet weak var timeslotOutlet: UITextField!
    
    let datePicker = UIDatePicker()
    
    var roomManager = RoomManager()
    
    var meetingRooms = [RoomModel]()
    
    var selectedTime: String = "08:00"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        //tableView.delegate = self
        
        tableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        
        timeslotOutlet.delegate = self
        
        roomManager.delegate = self
                
        roomManager.fetchRooms()
        
        showDatePicker()
        
    }
    
    func showDatePicker(){
        
        //Formate Date
        datePicker.datePickerMode = .dateAndTime
        datePicker.minuteInterval = 30
        
        //ToolBar
        let toolbar = UIToolbar();
        toolbar.sizeToFit()
        let doneButton = UIBarButtonItem(title: "Done", style: .plain, target: self, action: #selector(doneDatePicker));
        let spaceButton = UIBarButtonItem(barButtonSystemItem: UIBarButtonItem.SystemItem.flexibleSpace, target: nil, action: nil)
        let cancelButton = UIBarButtonItem(title: "Cancel", style: .plain, target: self, action: #selector(cancelDatePicker));
        
        toolbar.setItems([doneButton,spaceButton,cancelButton], animated: false)
        
        datePickerOutlet.inputAccessoryView = toolbar
        datePickerOutlet.inputView = datePicker
        
    }
    
    @objc func doneDatePicker(){
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd MMM yyyy"
        datePickerOutlet.text = dateFormatter.string(from: datePicker.date)
        
        let timeFormatter = DateFormatter()
        timeFormatter.dateFormat = "h:mm a"
        timeslotOutlet.text = timeFormatter.string(from: datePicker.date)
        
        let timeFormatter2 = DateFormatter()
        timeFormatter2.dateFormat = "HH:mm"
        selectedTime = timeFormatter2.string(from: datePicker.date)
        //print(timeFormatter2.string(from: datePicker.date))
        tableView.reloadData()
        
        datePickerOutlet?.resignFirstResponder()
        timeslotOutlet?.resignFirstResponder()
        
    }
    
    @objc func cancelDatePicker(){
        
        datePickerOutlet?.resignFirstResponder()
    }
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        if (textField == timeslotOutlet) {
            return false; //do not show keyboard nor cursor
        }
        return true
    }
    
    
    @IBAction func sortPressed(_ sender: UIButton) {
    }
    
    
    @IBAction func cameraPressed(_ sender: UIBarButtonItem) {
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == K.sortSegue {
            
            let destinationVC = segue.destination as! SortViewController
            destinationVC.meetingRooms = meetingRooms
            destinationVC.selectedTime = selectedTime
            destinationVC.delegate = self
            
        } else if segue.identifier == K.scanSegue {
            let destinationVC = segue.destination as! ScannerViewController
        }
    }
    
    
    
    
    
    
    
}



//MARK: - RoomManagerDelegate
extension RoomListingViewController: RoomManagerDelegate {
    func didDownloadRooms(_ roomManager: RoomManager, rooms: [RoomModel] ) {
        //print("didUpdateRoom")
        DispatchQueue.main.async {
            self.meetingRooms = rooms
            self.meetingRooms.sort(by: { $0.roomLevel < $1.roomLevel })
            //self.meetingRooms.sort(by: { $0.roomCapacity < $1.roomCapacity })
            //print(self.meetingRooms.count)
            self.tableView.reloadData()
            
        }
    }
    
    func didFailWithError(error: Error) {
        print(error)
    }
}


//MARK: - RoomManagerDelegate
extension RoomListingViewController: SortViewControllerDelegate {
    
    func didSortRooms(_ sortViewController: SortViewController) {
        //print("didSortRooms")
        
        meetingRooms = sortViewController.meetingRooms
        tableView.reloadData()
        
        
    }
}


//MARK: - UITableViewDataSource
extension RoomListingViewController: UITableViewDataSource {
    
//    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
//        return "Rooms"
//    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        //print(meetingRooms.count)
        return meetingRooms.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: K.RoomCellReuseIdentifier, for: indexPath) as! RoomTableViewCell
        
        let room = meetingRooms[indexPath.row]
        
        cell.nameLabel.text = room.roomName
        cell.levelLabel.text = "Level \(room.roomLevel)"
        
        if room.roomAvailability[selectedTime] == 1 {
            cell.availabilityLabel.text = "Available"
            cell.availabilityLabel.textColor = #colorLiteral(red: 0, green: 0.5603182912, blue: 0, alpha: 1)
        } else {
            cell.availabilityLabel.text = "Not Available"
            cell.availabilityLabel.textColor = #colorLiteral(red: 0.6000000238, green: 0.6000000238, blue: 0.6000000238, alpha: 1)
        }
        
        cell.capacityLabel.text = "\(room.roomCapacity) Pax"
        
        cell.roomBackgroundView.layer.cornerRadius = 10.0
        
        return cell
    }
}

//MARK: - UITableViewDelegate
//extension RoomListingViewController: UITableViewDelegate {
//    
//    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
//        // Background color
//        view.tintColor = .clear
//        
//        // Text Color
//        let headerView = view as! UITableViewHeaderFooterView
//        headerView.textLabel?.textColor = #colorLiteral(red: 0.8039215803, green: 0.8039215803, blue: 0.8039215803, alpha: 1)
//    }
//    
//}
