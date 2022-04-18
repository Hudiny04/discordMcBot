const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('starveall')
        .setDescription('starve all players on server'),
    async execute(interaction) {
         axios.get('http://127.0.0.1:8001/starve-all'
        ).then(function (response) {
            interaction.reply(response.data);
        }).catch(function (error) {
            console.log(error);
            interaction.reply("Something went wrong");
          })
        return
    },
};  