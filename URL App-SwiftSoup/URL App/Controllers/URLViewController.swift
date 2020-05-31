//
//  URLViewController.swift
//  URL App
//
//  Created by Weiping Tok on 22/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import UIKit
import SwiftSoup

class URLViewController: UITableViewController {
    
    var items = [ObjectModel]()
    var isAscending = true;
    
    var downloadManager = DownloadManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        print(isAscending)
        
        downloadManager.delegate = self
        
//        let item1 = ObjectModel(url: "https://sg.yahoo.com")
//        items.append(item1)
        
        tableView?.allowsMultipleSelection = true
    }
    
    @IBAction func addPressed(_ sender: UIBarButtonItem) {
        
        var textField = UITextField()
        
        let alert = UIAlertController(title: "Add Website Link", message: "", preferredStyle: .alert)
        
        let action = UIAlertAction(title: "Add", style: .default) { (action) in
            //what will happen once the user clicks the Add Item button on our UIAlert
            //print(textField.text)
            
            if let newURL = textField.text {
                // start first request
                self.downloadManager.fetchHTML(urlString: newURL)
            }            
        }
        
        alert.addTextField { (alertTextField) in
            alertTextField.placeholder = "e.g. https//2appstudio.com"
            textField = alertTextField
            //print(alertTextField.text)
        }
        
        alert.addAction(action)
        
        present(alert, animated: true, completion: nil)
    }
    
    
    @IBAction func removedPressed(_ sender: Any) {
        
        var array = [ObjectModel]()
        
        for i in 0..<items.count {
          if items[i].isSelected==false {
            print(items[i].url)
            array.append(items[i])
          }
        }
        
        items = array
        
        tableView.reloadData()

    }    
    
    @IBAction func organizePressed(_ sender: UIBarButtonItem) {
        
        if (isAscending) {
            self.items.sort(by: { $0.url > $1.url })
            isAscending = false
        } else {
            self.items.sort(by: { $0.url < $1.url })
            isAscending = true
        }
        
        self.tableView.reloadData()
    }
    
    //MARK: - TableView Datasource Methods
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //print(items.count)
        return items.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath) as! ItemTableViewCell
        
        let item = items[indexPath.row]
        
        cell.titleLabel.text = item.title
        cell.urlLabel.text = item.url
        cell.icon.image = item.icon
        cell.icon.layer.cornerRadius = 30
        cell.icon.layer.masksToBounds = true
        
        return cell
    }
    
    //MARK: - TableView Delegate methods
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
          items[indexPath.row].isSelected = true
       }
    
    override func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
          items[indexPath.row].isSelected = false
       }
    
}

//MARK: - DownloadManagerDelegate
extension URLViewController: DownloadManagerDelegate {
    func didUpdateItem(_ downloadManager: DownloadManager, download: ObjectModel ) {
        
        DispatchQueue.main.async {
            self.items.append(download)
            if (self.isAscending) {
                self.items.sort(by: { $0.url < $1.url })
            } else {
                self.items.sort(by: { $0.url > $1.url })
            }
            self.tableView.reloadData()
        }
        
    }
    
    func didFailWithError(error: String) {
        print(error)
        
        DispatchQueue.main.async {
            let alert = UIAlertController(title: "Alert", message: error, preferredStyle: .alert)

            alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))

            self.present(alert, animated: true)
        }                
        
    }
}
