package br.com.badcompany.sparkjavapetclinic.owner;

class PetController {

//    private final PetRepository pets;
//    private final OwnerRepository owners;
//
//    public PetController(PetRepository pets, OwnerRepository owners) {
//        this.pets = pets;
//        this.owners = owners;
//    }
//
//    @ModelAttribute("types")
//    public Collection<PetType> populatePetTypes() {
//        return this.pets.findPetTypes();
//    }
//
//    @ModelAttribute("owner")
//    public Owner findOwner(@PathVariable("ownerId") int ownerId) {
//        return this.owners.findById(ownerId);
//    }
//
//    @InitBinder("owner")
//    public void initOwnerBinder(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }
//
//    @InitBinder("pet")
//    public void initPetBinder(WebDataBinder dataBinder) {
//        dataBinder.setValidator(new PetValidator());
//    }
//
//    @GetMapping("/pets/new")
//    public String initCreationForm(Owner owner, ModelMap model) {
//        Pet pet = new Pet();
//        owner.addPet(pet);
//        model.put("pet", pet);
//        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//    }
//
//    @PostMapping("/pets/new")
//    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
//        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
//            result.rejectValue("name", "duplicate", "already exists");
//        }
//        owner.addPet(pet);
//        if (result.hasErrors()) {
//            model.put("pet", pet);
//            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//        } else {
//            this.pets.save(pet);
//            return "redirect:/owners/{ownerId}";
//        }
//    }
//
//    @GetMapping("/pets/{petId}/edit")
//    public String initUpdateForm(@PathVariable("petId") int petId, ModelMap model) {
//        Pet pet = this.pets.findById(petId);
//        model.put("pet", pet);
//        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//    }
//
//    @PostMapping("/pets/{petId}/edit")
//    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model) {
//        if (result.hasErrors()) {
//            pet.setOwner(owner);
//            model.put("pet", pet);
//            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//        } else {
//            owner.addPet(pet);
//            this.pets.save(pet);
//            return "redirect:/owners/{ownerId}";
//        }
//    }

}
